import React, { useEffect } from 'react'
import AddressCard from '../AddressCard/AddressCard'
import CartItem from '../Cart/CartItem'
import { useDispatch, useSelector } from 'react-redux'
import { getOrderById } from '../../../State/Order/Action'
import { useLocation } from 'react-router-dom'
import { CreatePayment } from '../../../State/Payment/Action'

const OrderSummary = () => {
  const dispatch=useDispatch();
  const location=useLocation();
  const {order}=useSelector(store=>store)
  const SearchParams=new URLSearchParams(location.search);
  const orderId=SearchParams.get("order_id");
  useEffect(()=>{
    dispatch(getOrderById(orderId));
  },[dispatch,orderId])

  const handleCheckout=()=>{
    dispatch(CreatePayment(orderId));
  }
  return (
    <div>
        <div className='p-5 shadow-lg rounded-s-md border '>

            <AddressCard address={order.order?.shippingAddress}/>
            

        </div>
        <div className='pt-5'>
      <div className='lg:grid grid-cols-3 lg:px-16 relative'>
        <div className='col-span-2'>
           {order.order?.orderItems.map((item)=><CartItem key={item.id} item={item}/>)}
        </div>
        <div className='px-5 sticky top-0 h-[100vh] mt-5'>
          <div className='border shadow-lg rounded-md'>
            <p className='uppercase font-bold opacity-55 pb-4 py-4 px-2'>Price Details</p>
            <hr />
            <div className='space-y-3 font-semibold mb-7'>
              <div className='flex justify-between pt-3 text-black'>
                <span className='px-1'>Price:</span>
                <span className='px-[.3rem]'>{order.order?.totalPrice}</span>
              </div>
              <div className='flex justify-between pt-3 text-black'>
                <span  className='px-1'>Discount:</span>
                <span className='text-green-600 px-[.3rem] '>-{order.order?.discount}</span>
              </div>
              <div className='flex justify-between pt-3 text-black'>
                <span className='px-1' >Delivery Charge:</span>
                <span className='text-green-600 px-[.3rem]'>FREE</span>
              </div>
              <hr />
              <div className='flex justify-between pt-3 text-black'>
                <span className='px-1'>Total Amount:</span>
                <span className='text-green-600 px-[.3rem]'>{order.order?.totalDisountedPrice}</span>
              </div>
              
            </div>
            <button
                className="mt-13 flex w-full items-center justify-center rounded-md border border-transparent
                            bg-indigo-600 px-8 py-3 text-base font-medium text-white hover:bg-indigo-700
                          focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 "
                          onClick={handleCheckout}>
                Place Order
              </button>
          </div>
        </div>

      </div>


    </div>
    </div>
  )
}

export default OrderSummary