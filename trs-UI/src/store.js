import { applyMiddleware, combineReducers, createStore } from "redux";
import {thunk} from "redux-thunk"
import CustomerReducer from "./redux/reducers/customer-reducre";
import ticketReducer from "./redux/reducers/ticketReducer";



const reducers=combineReducers({
    customerReducer:CustomerReducer,
    ticketReducer:ticketReducer
})
export const store=createStore(reducers,applyMiddleware(thunk))