import { AxiosRequestConfig, AxiosResponse } from "axios"
import { useStore } from "vuex"
import { Quiz } from "../../generated"
import { myApi } from "../MyApi"

const store = useStore()

export const getAllMyQuiz = async (username: string): Promise<Quiz[]> => {
  const res: AxiosResponse = await myApi().getMyQuiz(username)
  return res.data
}