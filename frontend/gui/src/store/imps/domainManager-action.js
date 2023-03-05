import { DOMAIN_MANAGER } from "../../sconst/domainManager";

const actDomainManagerAfterLogin = ({dispatch}) => {
	//<
	console.log(' --- actDomainManagerAfterLogin');
	//<
	for (const request of DOMAIN_MANAGER.LOAD_REQUESTS){
		dispatch(request);
	}
};

const actDomainManagerAfterLogout = ({dispatch}) => {
	//<
	console.log(' --- actDomainManagerAfterLogout');
	//<
	for (const request of DOMAIN_MANAGER.REMOVE_REQUESTS){
		dispatch(request);
	}
};

export {
	actDomainManagerAfterLogin,
	actDomainManagerAfterLogout
};
