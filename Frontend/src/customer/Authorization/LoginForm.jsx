import { Button, Grid, TextField } from '@mui/material'
import React from 'react'
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom'
import { login } from '../../State/Authentication/Action';


const LoginForm = () => {
  const navigate=useNavigate();
  const dispatch=useDispatch();
  const handleSubmit = (event) => {
    event.preventDefault();

    const data = new FormData(event.currentTarget);
    const Logindata = {
      email: data.get("email"),
      password: data.get("password")
    }
    dispatch(login(Logindata))  
    console.log(Logindata);

  }
  return (
    <div>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6}>
          </Grid>
          <Grid item xs={12}>
            <TextField
              id='email'
              name='email'
              label="Email"
              fullWidth
              autoComplete='email'
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              id='password'
              name='password'
              label="password"
              fullWidth
              autoComplete='password'
            />
          </Grid>
          <Grid className='flex items-center 'item xs={12}>
            <Button
              
              className='bg-blue-600 w-full'
              type='submit'
              variant='contained'
              size='large'
            >
              LogIn
            </Button>
          </Grid>

        </Grid>
      </form>
      <div className='mt-4'>
        <div className='flex justify-center'>
          <p>Dont have Account? <Button onClick={()=>navigate("/register")} size='small'>Create Account</Button></p>
        </div>
      </div>
    </div>
  )
}

export default LoginForm