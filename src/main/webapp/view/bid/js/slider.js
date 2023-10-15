const imgs = document.querySelectorAll('.img-select a');
        const imgBtns = [...imgs];
        let imgId = 1;

        function slide(){
            for(let imgItem of imgBtns){
                imgId = imgItem.dataset.id;
                slideImage();
            }
        }
        
        // imgItem.addEventListener('click', (event) => {
        //     console.log('are u ok?');
        //     event.preventDefault();
        //     imgId = imgItem.dataset.id;
        //     slideImage();
        // });

        function slideImage() {
            console.log('check');
            const displayWidth = document.querySelector('.img-showcase img:first-child').clientWidth;

            document.querySelector('.img-showcase').style.transform = `translateX(${- (imgId - 1) * displayWidth}px)`;
        }

        window.addEventListener('resize', slideImage);