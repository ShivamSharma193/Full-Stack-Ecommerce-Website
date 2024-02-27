
import { Button, Grid, TextField } from '@mui/material'
import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom'
import { getUSer, register } from '../../State/Authentication/Action';
import { store } from '../../State/store';

const RegisterForm = () => {
  const navigate=useNavigate();
  const dispatch= useDispatch();
  const jwt=localStorage.getItem("jwt");
  const {auth}=useSelector(store=>store); //use selector takes a arrow function which needs file name from which variable needs to be accessed dynamically

  useEffect(()=>{
    if(jwt){
      dispatch(getUSer(jwt))
    }
  },[jwt,auth.jwt])

  const handleSubmit=(event)=>{
    event.preventDefault();

    const data=new FormData(event.currentTarget);

    const userData={
      firstName:data.get("firstName"),
      lastName:data.get("lastName"),
      email:data.get("email"),
      password:data.get("password")
    }
    dispatch(register(userData))
    console.log(userData);

  }
  return (
    <div>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6}>
            <TextField 
            required 
            id='firstName'
            name='firstName'
            label="First Name"
            fullWidth
            autoComplete='given-name'

            />
          </Grid>
          <Grid item xs={12} sm={6}>
          <TextField 
            required 
            id='lastName'
            name='lastName'
            label="Last Name"
            fullWidth
            autoComplete='given-lastname'
            />
          </Grid >
          <Grid item xs={12}>
          <TextField 
            required 
            id='email'
            name='email'
            label="Email"
            fullWidth
            autoComplete='email'
            />
          </Grid >
          <Grid item xs={12}>
          <TextField 
            required 
            id='password'
            name='password'
            label="Password"
            fullWidth
            
            autoComplete='password'
            />
          </Grid >
          <Grid item xs={12}>
            <Button 
            className='bg-blue-600 w-full'
            type='submit'
            variant='contained'
            size='large'
            >
              Register
            </Button>

          
          </Grid >
          

        </Grid>


      </form>
      <div className='mt-4'>
        <div className='flex justify-center'>
          <p>Already Have account? <Button onClick={()=>navigate("/login")}>Login</Button></p>
        </div>
      </div>
    </div>
  )
}

export default RegisterForm