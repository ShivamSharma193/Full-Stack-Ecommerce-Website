import React from 'react'
import { Route, Routes } from 'react-router-dom'
import HomePage from '../customer/Pages/HomePage/HomePage'
import Cart from '../customer/components/Cart/Cart'
import Navigation from '../customer/components/Navigation/Navigation'
import Footer from '../customer/components/Footer/Footer'
import Product from '../customer/Product/Product'
import ProductDetails from '../customer/components/ProductDetails/ProductDetails'
import CheckOut from '../customer/components/CheckOut/CheckOut'
import MyOrders from '../customer/components/MyOrders/MyOrders'
import OrderDetails from '../customer/components/MyOrders/OrderDetails'
import PaymentSuccess from '../customer/components/Payment/PaymentSuccess'

const CustomerRoutes = () => {
  return (
    <div>
        <div>
        <Navigation/>
        </div>
        <Routes>
            <Route path='/login'element={<HomePage/>}></Route>
            <Route path='/register'element={<HomePage/>}></Route>
            <Route path='/'element={<HomePage/>}></Route>
            <Route path='/:levelOne/:levelTwo/:levelThree'element={<Product/>}></Route>
            <Route path='/product/:productId'element={<ProductDetails/>}></Route>
            <Route path='/cart'element={<Cart/>}></Route>
            <Route path='/checkout'element={<CheckOut/>}></Route>
            <Route path='/account/order'element={<MyOrders/>}></Route>
            <Route path='/account/order/:orderId'element={<OrderDetails/>}></Route>
            <Route path='/payment/:orderId'element={<PaymentSuccess/>}></Route>
            

              
       
        
       

            
        </Routes>
        <div>
        <Footer/>
        </div>
    </div>
  )
}

export default CustomerRoutes