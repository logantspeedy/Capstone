/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//var imgSources =['images/banners/attack.png','images/banners/claim.png'];
//var images = [];
//function preload(sources)
//{
//  for (i = 0, length = sources.length; i < length; ++i) {
//    images[i] = new Image();
//    images[i].src = sources[i];
//  }
//}
//preload(imgSources);
//
//function test(){
//    $('#test').empty().append(images[1]);
//}
var images = $('img');  // Get all images. (you'll want to modify the selector
                        //    to work with only the desired images)

images.load(function() {       // Add a load event hander that removes 
    images = images.not(this); //    each image from images once loaded.
});

setTimeout(function(){        // After 10 seconds, iterate over each remaining
    images.each(function() {  //     image, reloading each one
        // reload this image
    });
},10000); 