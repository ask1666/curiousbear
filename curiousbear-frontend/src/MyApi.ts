import {
	DefaultApi,
	Configuration,
	ConfigurationParameters,
} from '../generated'
import globalAxios from 'axios'
globalAxios.defaults.headers.common = {
	'X-Requested-With': 'XMLHttpRequest',
	'X-CSRFToken': 'example-of-custom-header',
}
export const myApi = () => {
	return new DefaultApi(
		new Configuration({
			/* apiKey: string | Promise<string> | ((name: string) => string) | ((name: string) => Promise<string>); */
			/* username: string; */
			/* password: string; */
			/* accessToken: "", */
			/* basePath: string */
			/* baseOptions: any; */
			/*  formDataCtor: new () => any; */
		} as ConfigurationParameters),
		undefined,
		globalAxios
	)
}
