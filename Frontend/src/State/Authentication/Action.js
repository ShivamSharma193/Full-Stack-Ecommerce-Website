import axios from "axios";
import { API_BASE_URL } from "../../BackendConfig/apiConfig";
import { GET_USER_FAILURE, GET_USER_REQUEST, GET_USER_SUCCESS, LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT, REGISTER_FAILURE, REGISTER_REQUEST, REGISTER_SUCCESS } from "./ActionType";

const token=localStorage.getItem("jwt");

const registerRequest=()=>({type:REGISTER_REQUEST});
//The payload is an optional field in the action object and can contain any type of data.
const registerSuccess=(user)=>({type:REGISTER_SUCCESS, payload:user});
const registerFailure=(error)=>({type:REGISTER_FAILURE,payload:error});

//function returning a function because of thunk
export const register=(userData)=> async (dispatch)=>{
    dispatch(registerRequest())
    try{
        const response=await axios.post( `${API_BASE_URL}/auth/signup`,userData); //takes a path and data to pass on that URL

        const user=response.data; //this will collect the data coming from backend
        //if user has jwt field then save it in local storage 
        if(user.jwt){
            localStorage.setItem("jwt",user.jwt)
        }
        console.log(user); 
        dispatch(registerSuccess(user.jwt));
    }
    catch(error){
        dispatch(registerFailure(error.message))

    }


} 



const loginRequest=()=>({type:LOGIN_REQUEST});
const loginSuccess=(user)=>({type:LOGIN_SUCCESS, payload:user});
const loginFailure=(error)=>({type:LOGIN_FAILURE,payload:error});

export const login=(userData)=>async(dispatch)=>{


    dispatch(loginRequest());
    try{
        const response=await axios.post(`${API_BASE_URL}/auth/signin`,userData);

        const user=response.data;
        if(user.jwt){
            localStorage.setItem("jwt",user.jwt);
        }
        console.log(user);
        dispatch(loginSuccess(user.jwt));

    }catch(error){
        dispatch(loginFailure(error.message));

    }
}

const getUserRequest=()=>({type:GET_USER_REQUEST});
const getUserSuccess=(user)=>({type:GET_USER_SUCCESS, payload:user});
const getUserFailure=(error)=>({type:GET_USER_FAILURE,payload:error});

export const getUSer=(jwt)=>async(dispatch)=>{


    dispatch(getUserRequest());
    try{
        const response=await axios.get(`${API_BASE_URL}/api/users/profile`,{
            headers:{
                "Authorization":`Bearer ${jwt}` //passes jwt in token
                
            }
        });

        const user=response.data;
        console.log(user);
        
        dispatch(getUserSuccess(user));

    }catch(error){
        dispatch(getUserFailure(error.message));

    }
}

export const logout=()=>(dispatch)=>{
    dispatch({type:LOGOUT,payload:null})//giving payload is optional
    localStorage.clear();
}
