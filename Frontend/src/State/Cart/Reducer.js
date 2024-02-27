import { ADD_ITEM_TO_CART_FAILURE, ADD_ITEM_TO_CART_REQUEST, ADD_ITEM_TO_CART_SUCCESS, GET_CART_FAILURE, GET_CART_REQUEST, GET_CART_SUCCESS, REMOVE_CART_ITEM_FAILURE, REMOVE_CART_ITEM_REQUEST, REMOVE_CART_ITEM_SUCCESS, UPDATE_CART_ITEM_FAILURE, UPDATE_CART_ITEM_REQUEST, UPDATE_CART_ITEM_SUCCESS } from "./ActionType"

const initialState={
    cart:null,
    loading:false,
    error:null,
    cartItems:[]
}

export const cartReducer=(state=initialState,action)=>{

    switch(action.type){
        case ADD_ITEM_TO_CART_REQUEST:
            return{...state,loading:true,error:null};

        case ADD_ITEM_TO_CART_SUCCESS:
            return{...state,loading:false,error:null,addcartItems:[...state.cartItems, action.payload.cartItems]};

        case ADD_ITEM_TO_CART_FAILURE:
            return{...state,loading:false,error:action.Payload};



        case GET_CART_REQUEST:
            return{...state,loading:true,error:null};

        case GET_CART_SUCCESS:
            return{...state,loading:false,error:null,cart:action.payload, cartItems:action.payload.cartItems};

        case GET_CART_FAILURE:
            return{...state,loading:false,error:action.payload};


        
        case REMOVE_CART_ITEM_REQUEST:
            return{...state,loading:true,error:null};

        case REMOVE_CART_ITEM_SUCCESS:
            return{...state,loading:false,error:null,removecartItems:action.payload //for every id ehich is not equal to action.payload, add it in cartItems and once equal thrn exclude it
            };

        case REMOVE_CART_ITEM_FAILURE:
            return{...state,loading:false,error:action.payload};



        case UPDATE_CART_ITEM_REQUEST:
            return {...state,loading:true,error:null};
        
        case UPDATE_CART_ITEM_SUCCESS:
            return{...state,loading:false,error:null,updateCartItems:action.payload //if item id is equal to action id then return with action.payload else keep item as item
            };

        case UPDATE_CART_ITEM_FAILURE:
            return{...state,loading:false,error:action.payload};

        
        default:
            return state;

    }
    
}

