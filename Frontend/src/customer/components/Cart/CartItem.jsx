import { RemoveCircleOutline } from '@mui/icons-material'
import { Button, IconButton } from '@mui/material'
import React from 'react'
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import { useDispatch } from 'react-redux';
import { removeItemFromCart, updateCartItem } from '../../../State/Cart/Action';

const CartItem = ({item}) => {
  const dispatch=useDispatch();
  const handleUpdate=(num)=>{
    const data={data:{quantity: item.quantity+num},cartItemId:item?.id} //num is passed from button
    dispatch(updateCartItem(data));
  }

  const handleRemove=()=>{
    dispatch(removeItemFromCart(item.id))
  }
  return (
    <div className='p-5 shadow-lg border rounded-md mb-4'>
      <div className='flex items-center'>
        <div className='w-[5rem] h-[5rem] lg:w-[9rem] lg:h-[10rem]'>
          <img className='w-full h-full object-cover object-top' src={item.product.imageSrc} alt='' />

        </div>
        <div className='ml-5 space-y-1'>
          <p className='font-semibold'>{item.product.title}</p>
          <p className='opacity-60'>Size: {item.size}, color: {item.product.color}</p>
          <p className='opacity-60 mt-2'>Seller: {item.product.brand}</p>

          <div className='flex items-center space-x-2 pt-3'>
            
            <p className='font-semibold'>{item.discountedPrice}</p>
            <p className='line-through opacity-50'>{item.price}</p>
            <p className='text-green-500 font-semibold ' >{item.product.discountPercent}%</p>
          </div>
        </div>
      </div>
      <div className='lg:flex items-center lg:space-x-10 pt-4'>
        <div className='flex items-center space-x-1'>
          <IconButton onClick={()=>handleUpdate(-1)} disabled={item.quantity<=1}>
            <RemoveCircleOutlineIcon></RemoveCircleOutlineIcon>
          </IconButton>
          <span className='py-1 px-7 border rounded-sm'>{item.quantity} </span>
          <IconButton onClick={()=>handleUpdate(1)}>
            <AddCircleOutlineIcon></AddCircleOutlineIcon>
          </IconButton>
          
        </div>
        <div>
          <Button onClick={handleRemove}>Remove</Button>
        </div>

      </div>
    </div>
  )
}

export default CartItem