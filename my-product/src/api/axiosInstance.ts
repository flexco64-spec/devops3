import axios from "axios";
import type { ProductDTO, ProductInput } from "../types/product";
import { data } from "react-router-dom";

const instance = axios.create({
  baseURL: "/api/product", //localhost:8080/api/product
  headers: { "Content-Type": "application/json" },
});

export const productApi = {
  getList: () => instance.get<ProductDTO[]>("/list").then((res) => res.data),
  getDetail: (num: number) =>
    instance.get<ProductDTO>(`/detail/${num}`).then((res) => res.data),
  insert: (data: ProductInput) =>
    instance.post<string>("/insert", data).then((res) => res.data),
  update: (data: ProductDTO) =>
    instance.put<string>("/update", data).then((res) => res.data),
  delete: (num: number) =>
    instance.delete<string>(`/delete/${num}`).then((res) => res.data),
};
