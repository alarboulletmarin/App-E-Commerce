export class Product {
  constructor(
    public code: string,
    public name: string,
    public description: string,
    public unitPrice: number,
    public imageUrl: string,
    public active: boolean,
    public unitsInStock: number,
    public dateCreated: Date,
    public lastUpdated: Date
  ) {}
}

export class ProductShort {
  constructor(
    public code: string,
    public name: string,
    public unitPrice: number,
    public imageUrl: string
  ) {}
}
