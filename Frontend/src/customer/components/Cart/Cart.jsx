import React, { useEffect } from 'react'
import CartItem from './CartItem'
import { Button } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import { getCartRequest } from '../../../State/Cart/Action'

const Cart = () => {
  const dispatch=useDispatch();
  const navigate=useNavigate();
  const {cart}=useSelector(store=>store);
  const handleCheckOut=()=>{
    navigate("/checkout/?step=2");
  }
  useEffect(()=>{
    dispatch(getCartRequest())
  },[cart.updateCartItems,cart.removecartItems,cart.addcartItems])
  return (
    <div>
      <div className='lg:grid grid-cols-3 lg:px-16 relative'>
        <div className='col-span-2'>
           {cart.cart?.cartItems.map((item)=><CartItem key={item.id} item={item} />)}
        </div>
        <div className='px-5 sticky top-0 h-[100vh] mt-5'>
          <div className='border shadow-lg rounded-md'>
            <p className='uppercase font-bold opacity-55 pb-4 py-4 px-2'>Price Details</p>
            <hr />
            <div className='space-y-3 font-semibold mb-7'>
              <div className='flex justify-between pt-3 text-black'>
                <span className='px-1'>Price:</span>
                <span className='px-[.3rem]'>{cart.cart?.totalPrice}</span>
              </div>
              <div className='flex justify-between pt-3 text-black'>
                <span  className='px-1'>Discount:</span>
                {/* i am accessing cart state which is a content in initial state in cart Reducer.js. it is null initially so the ? in 
                below query states that if cart is not null then find discount from availabe data else will get undefined */}
                <span className='text-green-600 px-[.3rem] '>-{cart.cart?.discount}</span> 
                
              </div>
              <div className='flex justify-between pt-3 text-black'>
                <span className='px-1' >Delivery Charge:</span>
                <span className='text-green-600 px-[.3rem]'>FREE</span>
              </div>
              <hr />
              <div className='flex justify-between pt-3 text-black'>
                <span className='px-1'>Total Amount:</span>
                <span className='text-green-600 px-[.3rem]'>{cart.cart?.totalDiscountPrice}</span>
              </div>
              
            </div>
            <button onClick={handleCheckOut}
                className="mt-13 flex w-full items-center justify-center rounded-md border border-transparent
                            bg-indigo-600 px-8 py-3 text-base font-medium text-white hover:bg-indigo-700
                          focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 ">
                CheckOut
              </button>
          </div>
        </div>

      </div>


    </div>
  )
}

export default Cart