import React from "react";
//we are going to make a products slidebar card in which all listed products can be displayed
const HomeCard=({product})=>{
    return (
        //main div
        <div className="cursor-poiner flex flex-col items-centre bg-gray-300 rounded-lg shadow-lg
         overflow-hidden w-[15rem] mx-3">
            {/* product div */}
            <div className='h-[15rem] w-[15rem]'>
                <img className="object-cover object-top w-full h-full " src={product.image} alt=""/>
            </div>
            <div className="p-4">
                <h3 className="text-lg font-medium text-gray-800">{product.brand}</h3>
                <p className="mt-2 text-sm text-gray-500">{product.title}</p>

            </div>
            
        </div>
    );
}
export default HomeCard;