import { api } from "../../BackendConfig/apiConfig";
import { CREATE_PAYMENT_FAILURE, CREATE_PAYMENT_REQUEST, UPDATE_PAYMENT_FAILURE, UPDATE_PAYMENT_REQUEST } from "./ActionType"

export const CreatePayment=(orderId)=>async(dispatch)=>{

    dispatch({type:CREATE_PAYMENT_REQUEST})
    try {
        const {data}=await api.post(`/api/payment/${orderId}`,{});
        console.log(data)
        if(data.payment_link_url){
            window.location.href=data.payment_link_url;
        }
        
        
    } catch (error) {
        console.error("Razorpay API Error:", error.response.data);
        dispatch({type:CREATE_PAYMENT_FAILURE,payload:error.message});
    }
}

export const UpdatePayment=(reqData)=>async(dispatch)=>{

    dispatch({type:UPDATE_PAYMENT_REQUEST})
    try {
        const {data}=await api.get(`/api/payment?payment_id=${reqData.orderId}&order_id=${reqData.orderId}`);
        if(data.payment_link_url){
            window.location.href=data.payment_link_url;
        }
        console.log("updated details",data);
        
    } catch (error) {
        dispatch({type:UPDATE_PAYMENT_FAILURE,payload:error.message});
    }
}