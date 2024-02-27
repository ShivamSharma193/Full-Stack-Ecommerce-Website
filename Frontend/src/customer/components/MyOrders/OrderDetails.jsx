import React from 'react'
import AddressCard from '../AddressCard/AddressCard'
import OrderTracker from './OrderTracker'

const OrderDetails = () => {
  return (
    <div className='px-5  lg:px-20'>
        <div className='font-bold mb-4 text-2xl'>
        Delivery Address
        </div>
        <AddressCard/>
        <div className='py-20'>
        <OrderTracker activeStep={3}/>
        </div>
        <div className='flex justify-center items-center '>
            <p className='text-4xl '>Thank you for ordering!!</p>
        </div>
    </div>
    
  )
}

export default OrderDetails