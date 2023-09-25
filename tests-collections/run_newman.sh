#!/bin/bash
cd "$(dirname "$0")"

# Define some color codes
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Function for loading effect
loading() {
    pid=$1
    spin='-\|/'

    printf "${GREEN}Running $(basename "$collection")... "
    i=0
    while kill -0 $pid 2>/dev/null
    do
        i=$(( (i+1) %4 ))
        printf "\b${spin:$i:1}"
        sleep .1
    done
    printf "${NC}\b"
}

# Display script usage
display_usage() {
    echo -e "\n${GREEN}Usage:${NC}"
    echo "run_newman.sh [-h]"
    echo ""
    echo "Options:"
    echo "-h, --help        Show this help message and exit"
    echo ""
    echo "This script runs Postman collections using Newman. You can choose to run specific collections or all collections."
    echo ""
    echo "Example usage:"
    echo "Run all collections: ./run_newman.sh"
    echo "Display this help message: ./run_newman.sh -h"
}

# Get the latest run number
run_number=0
if ls newman_run_results/run_* 1>/dev/null 2>&1; then
    latest_run=$(ls -r newman_run_results/run_* | head -n 1)
    run_number=$(basename "$latest_run" | awk -F '_' '{print $2}')
fi
run_number=$((run_number + 1))

# Check if -h or --help option was given
if [[ "$1" == "-h" || "$1" == "--help" ]]; then
    display_usage
    exit 0
fi

while true; do
    echo -e "${GREEN}---------------------------------------------${NC}"
    echo "" # Blank line for visual separation

    # List all Postman collections
    echo -e "${GREEN}Available Postman collections:${NC}"
    echo "" # Blank line for visual separation
    collections=(collections/*.postman_collection.json)
    for index in "${!collections[@]}"; do
        echo -e "${BLUE}$((index + 1)).$(basename "${collections[$index]}")${NC}"
    done
    echo "" # Blank line for visual separation

    # Get user choice
    echo "Do you want to run a specific collection(s) or all collections?"
    echo "1. Specific collection(s)"
    echo "2. All collections"
    echo "3. Quit"
    read -p "Your choice (1-3): " choice
    echo "" # Blank line for visual separation

    case $choice in
    1)
        while true; do
            echo "Enter the number(s) of the collection(s) to run (separated by commas): "
            read collections_to_run
            IFS=',' read -ra indices <<<"$collections_to_run"
            # Check if all entered collection numbers are valid
            all_valid=true
            for index in "${indices[@]}"; do
                if [[ "$index" -gt "${#collections[@]}" || "$index" -lt 1 ]]; then
                    echo -e "${RED}Invalid collection number: $index${NC}"
                    all_valid=false
                fi
            done
            if [ "$all_valid" = true ]; then
                break
            fi
        done
        ;;
    2)
        indices=($(seq 1 ${#collections[@]}))
        ;;
    3)
        echo -e "${GREEN}Exiting..."
        exit 0
        ;;
    *)
        echo "Invalid option. Please enter a number between 1 and 3."
        continue
        ;;
    esac

    # Get user iteration collection
    while true; do
        echo "How many times do you want to run the collection(s)?"
        read -p "Enter a number: " iterations
        echo "" # Blank line for visual separation

        # Validate iteration number
        if [[ "$iterations" =~ ^[0-9]+$ ]]; then
            break
        else
            echo -e "${RED}Invalid number. Please enter a valid number.${NC}"
        fi
    done

    # Create a unique directory for this run
    run_dir="newman_run_results/run_${run_number}_$(date +'%Y-%m-%d_%H-%M-%S')"
    mkdir -p "$run_dir"

    for index in "${indices[@]}"; do
        if [[ "$index" -le "${#collections[@]}" ]]; then
            collection="${collections[$((index - 1))]}"
            newman run "$collection" -e "environment/ENV_App-e-commerce.postman_environment.json" -r htmlextra --reporter-htmlextra-export "$run_dir/$(basename "$collection" .json).html" -n "$iterations" &
            pid=$!
            loading $pid
            wait $pid
            ret_code=$?
            if [ $ret_code -eq 0 ]; then
                echo "" # Blank line for visual separation
                echo -e "${GREEN}Execution of $(basename "$collection") succeeded.${NC}"
            else
                echo "" # Blank line for visual separation
                echo -e "${RED}Execution of $(basename "$collection") failed.${NC}"
            fi
            echo "" # Blank line for visual separation
        fi
    done

    echo "" # Blank line for visual separation
    echo -e "${GREEN}All done! The results are stored in $run_dir directory${NC}"
    echo "" # Blank line for visual separation
    echo -e "${GREEN}---------------------------------------------${NC}"

    # Increment the run number
    run_number=$((run_number + 1))
done
