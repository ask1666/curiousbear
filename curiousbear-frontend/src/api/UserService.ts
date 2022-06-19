import { myApi } from '../MyApi'
import { CreateUserDto } from "../../generated"
import { AxiosResponse } from 'axios'
import Axios from 'axios'
import { BASE_PATH } from '../../generated/base'

export const createUser = (command: CreateUserDto) => {
  myApi()
				.createUser(command)
				.catch((e: any) => {
          console.log(e)
				})
}

export const login = async (username: String, password: String) : Promise<AxiosResponse> => {
  const res = await Axios.post(BASE_PATH + '/login', {
    username, 
    password 
  })
  return res
}