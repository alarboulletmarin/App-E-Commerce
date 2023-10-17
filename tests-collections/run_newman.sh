#!/bin/bash

cd "$(dirname "$0")"

# Define some color codes
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Find the first environment file
environment_file=$(find environment -name "*.postman_environment.json" | head -n 1)

# Get the latest run number
run_number=0
if ls newman_run_results/run_* 1>/dev/null 2>&1; then
    latest_run=$(ls -r newman_run_results/run_* | head -n 1)
    run_number=$(basename "$latest_run" | awk -F '_' '{print $2}')
fi
run_number=$((run_number + 1))

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
    echo "This script runs Postman collections using Newman."
    echo ""
    echo "Example usage:"
    echo "Run all collections: ./run_newman.sh"
    echo "Display this help message: ./run_newman.sh -h"
}

# ...
chooseCollection() {
    echo -e "${GREEN}Available Postman collections:${NC}"
    collections=(collections/*.postman_collection.json)
    for index in "${!collections[@]}"; do
        echo -e "${BLUE}$((index + 1)).$(basename "${collections[$index]}")${NC}"
    done

    echo -e "${GREEN}---------------------------------------------${NC}"

    echo "Do you want to run a specific collection(s) or all collections?"
    echo "[1] Specific collection(s)"
    echo "[2] All collections (default)"
    echo "[3] Quit"
    read -p "your choice --> " choice

    choice=${choice:-2}

    case $choice in
    1)
        echo "Enter the number(s) of the collection(s) to run (separated by commas, default is 1): "
        read collections_to_run
        collections_to_run=${collections_to_run:-1}
        IFS=',' read -ra indices <<<"$collections_to_run"
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
        return 1
        ;;
    esac

    return 0
}

# ...
setIterations() {
    echo "How many times do you want to run the collection(s)? [default is 1]"
    read iterations
    iterations=${iterations:-1}
}

# ...
runCollections() {
    run_dir="newman_run_results/run_${run_number}_$(date +'%Y-%m-%d_%H-%M-%S')"
    mkdir -p "$run_dir"

    for index in "${indices[@]}"; do
        if [[ "$index" -le "${#collections[@]}" ]]; then
            collection="${collections[$((index - 1))]}"
            echo "Debug: collection = $collection"
            newman run "$collection" -e "$environment_file" -r htmlextra --reporter-htmlextra-export "$run_dir/$(basename "$collection" .json).html" -n "$iterations" &
            pid=$!
            loading $pid
            wait $pid
            ret_code=$?
            if [ $ret_code -eq 0 ]; then
                echo -e "${GREEN}Execution of $(basename "$collection") succeeded.${NC}"
            else
                echo -e "${RED}Execution of $(basename "$collection") failed.${NC}"
            fi
        fi
    done

    echo -e "${GREEN}All done! The results are stored in $run_dir directory${NC}"
    run_number=$((run_number + 1))
}

# ...
mainMenu() {
    while true; do
        echo -e "${GREEN}---------------------------------------------${NC}"
        if chooseCollection; then
            setIterations
            runCollections
        fi
        echo -e "${GREEN}---------------------------------------------${NC}"
    done
}

# Check if -h or --help option was given
if [[ "$1" == "-h" || "$1" == "--help" ]]; then
    display_usage
    exit 0
fi

mainMenu