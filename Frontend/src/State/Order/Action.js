import { useNavigate } from "react-router-dom"
import { api,API_BASE_URL } from "../../BackendConfig/apiConfig"
import { CREATE_ORDER_FAILURE, CREATE_ORDER_REQUEST, CREATE_ORDER_SUCCESS, GET_ORDER_BY_ID_FAILURE, GET_ORDER_BY_ID_REQUEST, GET_ORDER_BY_ID_SUCCESS } from "./ActionType"

export const createOrder=(reqData)=>async(dispatch)=>{
    // const navigate=useNavigate();

    dispatch({type:CREATE_ORDER_REQUEST})
    try{

        //reqdata is data sent to backend to run query and payload is data received 
        const {data}= await api.post(`/api/orders/`,reqData.address);
        dispatch({type:CREATE_ORDER_SUCCESS , payload:data})
        console.log("order By id",data);

        if (data.id) {
            // Correct usage of navigate here
            reqData.navigate({ search: `step=3&order_id=${data.id}` });
          }

    }catch(error){
        dispatch({type:CREATE_ORDER_FAILURE, payload:error.message})

    }
}

export const getOrderById=(orderId)=>async(dispatch)=>{
    dispatch({type:GET_ORDER_BY_ID_REQUEST})
    try{
        const {data}=await api.get(`/api/orders/${orderId}`);
        console.log("order By id",data);
        dispatch({type:GET_ORDER_BY_ID_SUCCESS,payload:data})
        
    }
    catch(error){
        dispatch({type:GET_ORDER_BY_ID_FAILURE,payload:error.message})
    }

}

// export const getOrderHistory=(reqData)=>async(dispatch)=>{
//     dispatch({type:GET_ORDER_HISTORY_REQUEST})
//     try{
//         const {data}=await api.get(`/api/orders/user`,reqData.data)
//         dispatch({type:GET_ORDER_HISTORY_SUCCESS,payload:data})
//     }
//     catch(error){
//         dispatch({type:GET_ORDER_HISTORY_FAILURE,payload:error.message})
//     }

// }