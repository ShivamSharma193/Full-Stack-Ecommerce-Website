import React from "react";
import MainCarousel from "../../components/HomeCarousel/MainCarousel";
import HomeCard from "../../components/HomeCard/HomeCard";
import HomeCardCarosel from "../../components/HomeCardCarosel/HomeCardCarosel";
import Men_Kurtas from "../../components/HomeCard/Kurtas";

import Footer from "../../components/Footer/Footer";

const HomePage=()=>{
    return(
        <div>
           <MainCarousel/>
           <div className="space-y-10 py-20 flex-col justify-center" >
            <HomeCardCarosel data={Men_Kurtas} sectionName={"Men's Kurta"}/>
            <HomeCardCarosel data={Men_Kurtas} sectionName={"Women's Lhenga"}/>
            <HomeCardCarosel data={Men_Kurtas} sectionName={"Men's shoes"}/>
            <HomeCardCarosel data={Men_Kurtas} sectionName={"Women's Saree"}/>
           </div>
           
        </div>
    )
}
export default HomePage;