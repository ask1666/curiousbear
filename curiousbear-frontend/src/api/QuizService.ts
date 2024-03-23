import { AxiosRequestConfig, AxiosResponse } from "axios"
import { Quiz, CreateQuizDto, QuizAntity, QuizEntry } from "../../generated"
import { myApi } from "../MyApi"
import { store } from '../Store'

export const getAllMyQuiz = async (username: string): Promise<Quiz[]> => {
  const res: AxiosResponse = await myApi().getMyQuiz(username)
  return res.data
}

export const createQuiz = (title: String, description: string, isPublic: boolean) => {
  myApi()
  .createQuiz({
    username: store.getters.getUser(),
    title,
    description,
    isPublic,
    quizEntry: [] as QuizEntry[]
  } as CreateQuizDto)
  .catch((e: any) => {
    console.log(e)
  })
}