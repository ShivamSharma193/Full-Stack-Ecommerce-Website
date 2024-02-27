import { Grid } from '@mui/material'
import React from 'react'
import AdjustIcon from '@mui/icons-material/Adjust';
import { useNavigate } from 'react-router-dom';

const OrderCard = () => {
    const navigate=useNavigate();

    return (
        <div onClick={()=>navigate(`/account/order/${5}`)} className='mb-5 p-3 shadow-md hover:shadow-2xl border rounded-md'>
            <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>
                <Grid item xs={6}>
                    <div className='flex cursor-pointer'>
                        <img className='w-[5rem] h-[5rem] object-cover object-top' src='https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.XbH1iGOIR3UiVE2Jff5KOQHaKU%26pid%3DApi&f=1&ipt=5e162f53652db593743627395d69e49a5cc5160cf75fc3b1366c5871d72cb343&ipo=images'
                            alt='' />
                        <div className='ml-5 space-y-2'>
                            <p className='font-semibold'>Lhenga for wedding. Look Extraordinary!</p>
                            <p className='opacity-60'>Size: M</p>
                            

                        </div>
                    </div>

                </Grid>
                <Grid item xs={2}>
                    <p>10000</p>

                </Grid>
                <Grid item xs={4}>
                    {true && <div>
                    <p>
                        <AdjustIcon sx={{width:"15px", height:"15px"}} className='text-green-600 mr-2 text-sm'/>
                        <span>
                           Expected deliverey on Jan 05
                        </span>
                    </p>
                    <p className='text-xs px-7'>Your item has been delivered</p>
                    </div>}
                    {false && <p>
                        <span>
                           Expected deliverey on Jan 05
                        </span>
                    </p>}

                </Grid>

            </Grid>
        </div>
    )
}

export default OrderCard