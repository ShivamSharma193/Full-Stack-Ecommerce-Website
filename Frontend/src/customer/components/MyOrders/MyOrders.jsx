import { Grid } from '@mui/material'
import React from 'react'
import OrderCard from './OrderCard'

const orderStatus=[
  {label:"On The Way",value:"on_the_way"},
  {label:"Delivered",value:"delivered"},
  {label:"Cancelled",value:"cancelled"},
  {label:"Returned",value:"returned"},

]
const MyOrders = () => {
  return (
    <div className='px-5 lg:px-20'>
        <Grid container sx={{justifyContent:"space-between"}}>
          <Grid item xs={2.5}>
            <div className='h-auto shadow-lg bg-white p-5 sticky top-5'>
              <h1 className='font-bold text-large'>
                Filter
              </h1>
              <div className='space-y-4 mt-10'>
                <h1 className='font-semibold'>ORDER STATUS</h1>
                {orderStatus.map((option)=><div className='flex-items-centre'>
                  <input type='checkbox' defaultValue={option.value} className='h-4 w-4 border-gray-400 text-blue-700 focus:ring-blue-400'/>
                  <label htmlFor={option.value} className='ml-3 text-sm'>{option.label}</label>
                </div>)}

              </div>

            </div>

          </Grid >
          <Grid item xs={9}>
          {[1,1,1,1].map((item)=><OrderCard/>)}
          </Grid>
          
          

        </Grid>
    </div>
  )
}

export default MyOrders