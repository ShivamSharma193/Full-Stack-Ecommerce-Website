import logo from './logo.svg';
import './App.css';
import Navigation from './customer/components/Navigation/Navigation';
import HomePage from './customer/Pages/HomePage/HomePage';
import Product from './customer/Product/Product';
import Footer from './customer/components/Footer/Footer';
import ProductDetails from './customer/components/ProductDetails/ProductDetails';
import Cart from './customer/components/Cart/Cart';
import CheckOut from './customer/components/CheckOut/CheckOut';
import MyOrders from './customer/components/MyOrders/MyOrders';
import OrderDetails from './customer/components/MyOrders/OrderDetails';
import { Route, Routes } from 'react-router-dom';
import CustomerRoutes from './Routes/CustomerRoutes';

function App() {
  return (
    <div className="">
      <Routes>
        <Route path='/*' element={<CustomerRoutes/>}>

        </Route>
      </Routes>
      
    </div>
  );
}

export default App;
