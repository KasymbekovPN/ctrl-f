import Vuex from "vuex";
import connection from "./modules/connection";
import i18n from "./modules/i18n";
import subscription from "./modules/subscription";
import auth from "./modules/auth";
import userProfile from "./modules/userProfile";

export default new Vuex.Store({
	modules: {
		connection,
		i18n,
		subscription,
		auth,
		userProfile
	}
});
