
import { FIND_PRDOUCT_BY_ID_FAILURE, FIND_PRDOUCT_BY_ID_REQUEST, FIND_PRDOUCT_BY_ID_SUCCESS, FIND_PRODUCT_BY_CATEGORY_FAILURE, FIND_PRODUCT_BY_CATEGORY_REQUEST, FIND_PRODUCT_BY_CATEGORY_SUCCESS } from "./ActionType"
const initialState={
    products:[],
    product:null,
    loading:false,
    error:null
}

export const customerProductReducer=(state=initialState,action)=>{

    switch (action.type) {
            case FIND_PRDOUCT_BY_ID_REQUEST:
            case FIND_PRODUCT_BY_CATEGORY_REQUEST:
                return{...state, loading:true, error:null}

            case FIND_PRODUCT_BY_CATEGORY_SUCCESS:
                return{...state,loading:false,products:action.payload ,error:null}

            case FIND_PRDOUCT_BY_ID_SUCCESS:
                return{...state,loading:false,product:action.payload ,error:null}
            
            case FIND_PRDOUCT_BY_ID_FAILURE:
            case FIND_PRODUCT_BY_CATEGORY_FAILURE:
                return{...state,loading:false,error:action.payload}

            
    
        default:
            return state;
    }

}