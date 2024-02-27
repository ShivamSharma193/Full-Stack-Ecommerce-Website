import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useParams } from 'react-router-dom';
import { store } from '../../../State/store';
import { getOrderById } from '../../../State/Order/Action';
import { UpdatePayment } from '../../../State/Payment/Action';
import { Alert, AlertTitle, Button, Grid } from '@mui/material';
import OrderTracker from '../MyOrders/OrderTracker';


const PaymentSuccess = () => {
  const dispatch = useDispatch();
  // const {order}=useSelector(store=>store);
  const [paymentId, setPaymentId] = useState();
  const [paymentStatus, setPaymentStatus] = useState();

  useEffect(() => {
    const urlParam = new URLSearchParams(window.location.search);
    setPaymentId(urlParam.get("razorpay_payment_id"));
    setPaymentStatus(urlParam.get("razorpay_payment_link_status"));


  }, [])

  const { orderId } = useParams();
  console.log("orderid", orderId);

  useEffect(() => {
    const data = { orderId, paymentId }
    dispatch(getOrderById(orderId))
    dispatch(UpdatePayment(data))
  }, [orderId, paymentId])





  return (
    <div className='px-2 lg:px-36'>
      <div className='flex flex-col justify-center items-center'>
        <Alert
          variant='filled'
          severity='success'
          sx={{ mb: 6, width: "fit-content" }}
        >
          <AlertTitle>
            Payment Success!
          </AlertTitle>
          Your Order has been placed.
          Thank you for Shopping!!

        </Alert>


      </div>
      <div className='mt-10'>
      <OrderTracker activeStep={1} />
      </div>

      <div className='flex flex-col justify-center items-center mt-40'>

        <Button variant='contained'>
          Continue Shopping
        </Button>
      </div>

    </div>

  )
}

export default PaymentSuccess;