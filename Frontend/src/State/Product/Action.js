import { FIND_PRDOUCT_BY_ID_FAILURE, FIND_PRDOUCT_BY_ID_REQUEST, FIND_PRDOUCT_BY_ID_SUCCESS, FIND_PRODUCT_BY_CATEGORY_FAILURE, FIND_PRODUCT_BY_CATEGORY_REQUEST, FIND_PRODUCT_BY_CATEGORY_SUCCESS } from "./ActionType";
import { api,API_BASE_URL } from "../../BackendConfig/apiConfig" 
import { Await } from "react-router-dom";
export const findProducts = (reqData) =>async (dispatch) => {
    dispatch({type:FIND_PRODUCT_BY_CATEGORY_REQUEST})

//these are all the variables that backend needs to run the filter query
    const { colors,
         sizes, 
         minPrice,
          maxPrice, 
          minDiscount, 
          category, stock, 
          sort, pageNumber, 
          pageSize }=reqData;

          try{
            const {data} =await api.get(`/api/products?color=${colors}&sizes=${sizes}&minPrice=${minPrice}&maxPrice=${maxPrice}&minDiscount=${minDiscount}&category=${category}&stock=${stock}&sort=${sort}&pageNumber=${pageNumber}&pageSize=${pageSize}`)

            console.log("product Data",data);
            
            dispatch({type:FIND_PRODUCT_BY_CATEGORY_SUCCESS,payload:data})
          }
          catch(error){
            dispatch({type:FIND_PRODUCT_BY_CATEGORY_FAILURE,payload:error.message})

          }
};

// export const findProductsById = (reqData) =>async (dispatch) => {
//     dispatch({type:FIND_PRDOUCT_BY_ID_REQUEST})
// //these are all the variables that backend needs to run the filter query
//     const {productId}=reqData;
//     console.log("prod",productId)
//           try{
//             const {data} = await api.get(`/api/products/id/${productId}`)
//             console.log("productdata",data);
            
//             dispatch({type:FIND_PRDOUCT_BY_ID_SUCCESS,payload:data})
//           }
//           catch(error){
//             dispatch({type:FIND_PRDOUCT_BY_ID_FAILURE,payload:error.message})

//           }
// }
export const findProductsById=(reqData)=>async (dispatch)=>{
  dispatch({type:FIND_PRDOUCT_BY_ID_REQUEST})
  const {productId}=reqData;
  console.log("id",productId);
  try{
    //http://localhost:8080/api/products/id/253
    const {data}=await api.get(`/api/products/id/${productId}`);
    console.log('data',data);

    dispatch({type:FIND_PRDOUCT_BY_ID_SUCCESS,payload:data})
  }catch(error){
    //console.error("error",error)
    dispatch({type:FIND_PRDOUCT_BY_ID_FAILURE,payload:error.message})
  }
};