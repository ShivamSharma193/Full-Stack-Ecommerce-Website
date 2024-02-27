
import AliceCarousel from 'react-alice-carousel'
import HomeCard from '../HomeCard/HomeCard';
import { Button } from '@mui/material';

import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';




const HomeCardCarosel = ({data,sectionName}) => {

    //below responive says if with is 0 px then show 1 item of card
    //if 720 then 3 and 4 for 1024
    const responsive = {
        0: { items: 1 },
        720: { items: 2 },
        1024: { items: 5 },

    };
    const renderPrevButton = () => {
        return (<Button variant='contained' className='z-50 bg-white '
        sx={{ position: 'absolute', top: '10rem', right: '-3rem', transform: 'Rotate(90deg)', bgcolor: "white" }}>
        <ChevronLeftIcon sx={{ transform: " Rotate(90deg)", color: "black" }} />
    </Button>)
      };
      const renderNextButton = () => {
        return (<Button  variant='contained' className='z-50 bg-white'
        sx={{ position: 'absolute', top: '10rem', left: '-3rem', transform: 'Rotate(90deg)', bgcolor: "white" }}>
        <ChevronLeftIcon sx={{ transform: 'Rotate(270deg)', color: "black" }} />
    </Button>)
      };

   

    const items = data.map((item) => <HomeCard product={item}/>) 
    return (
        <div className='relative px-3 lg:px-8 '>
            <h2 className='text-2xl font-extrabold py-5'>{sectionName}</h2>
            <div className='relative p-10' >
                <AliceCarousel
                    items={items}
                    infinite
                    responsive={responsive}
                    disableDotsControls
                    renderPrevButton={renderNextButton}
                    renderNextButton={renderPrevButton}
                />
            </div>
        </div>
    )
}

export default HomeCardCarosel