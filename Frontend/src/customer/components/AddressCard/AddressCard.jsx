import React from 'react'

const AddressCard = ({address}) => {
    return (
        <div>
            <p className='font-semibold'>{address.firstName +" "+address.lastName}</p>
            <p>{address.streetAddress+" "+address.city+" "+address.state+" "+address.zipCode}</p>
            <div>
            <p className='font-semibold'>Phone Number</p>
            <p>9359378757</p>
            </div>
        </div>
          
       
    )
}

export default AddressCard