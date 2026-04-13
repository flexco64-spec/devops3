export interface ProductDTO {
  num: number;
  name: string;
  price: number;
  amount: number;
}

export type ProductInput = Omit<ProductDTO, "num">;
