import { Button, Grid, Typography } from '@mui/material';
import React from 'react'

export const Footer = () => {
  return (
    <div>
        <Grid className='bg-black text-white text-center mt-10 '
        container sx={{bgcolor:"black",color:"white",py:3}}
        >
            <Grid item xs={12} sm={6} md={3}>
                <Typography className='pb-5' variant='h6'>Company</Typography>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>About</Button>
                </div>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>Blog</Button>
                </div>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>Press</Button>
                </div>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>Jobs</Button>
                </div>
            </Grid>
            <Grid item xs={12} sm={6} md={3}>
                <Typography className='pb-5' variant='h6'>Solutions</Typography>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>Analysis</Button>
                </div>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>Commerce</Button>
                </div>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>Support</Button>
                </div>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>Marketing</Button>
                </div>
            </Grid>
            <Grid item xs={12} sm={6} md={3}>
                <Typography className='pb-5' variant='h6'>Documentation</Typography>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>Guides</Button>
                </div>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>Terms and Conditions</Button>
                </div>
            </Grid>
            <Grid item xs={12} sm={6} md={3}>
                <Typography className='pb-5' variant='h6'>Legal</Typography>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>Claim</Button>
                </div>
                <div>
                <Button className='pb-5' variant='h6' gutterBottom>Privacy</Button>
                </div>
            </Grid>
            <Grid className='pt-20' item xs={12}>
            <Typography variant='body1' component="p" align='center'>&copy; 2024 FASHION POINT.</Typography>
            <Typography variant='body2' component="p" align='center'>All rights reserved</Typography>
            </Grid>
        </Grid>
    </div>
  )
}
export default Footer;
