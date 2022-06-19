import {
	DefaultApi,
	Configuration,
	ConfigurationParameters,
} from '../generated'
import globalAxios from 'axios'

export const myApi = () => {
	return new DefaultApi(
		new Configuration({
			/* apiKey: string | Promise<string> | ((name: string) => string) | ((name: string) => Promise<string>); */
			/* username: string; */
			/* password: string; */
			/* accessToken: useStore()?.getters?.getToken?.access_token ?? "", */
			/* basePath: string */
			/* baseOptions: any; */
			/*  formDataCtor: new () => any; */
		} as ConfigurationParameters),
		undefined,
		globalAxios
	)
}
