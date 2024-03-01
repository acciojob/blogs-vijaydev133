package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        //Adding in blog list
        List<Image> imageList=blog.getImageList();
        imageList.add(image);

        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //countImagesInScreen: given an image id and a screen size, find the number of images of given size
        // that can fit completely into the screen with given dimensions.
        // For example, a screen with dimensions 4X4, can completely fit 4 images, each having dimensions 2X2.
        int count=0;
        String[] dimarr=screenDimensions.split("X");
        Image image=imageRepository2.findById(id).get();
        String dimensionOfImage=image.getDimensions();
        String[] imgarr=dimensionOfImage.split("X");
        int imgx=Integer.parseInt(imgarr[0]);
        int imgy=Integer.parseInt(imgarr[1]);

        int dimx=Integer.parseInt(dimarr[0]);
        int dimy=Integer.parseInt(dimarr[1]);

        //4X4 = 4/2*4/2 = 4 images
        int countx=dimx/imgx;
        int county=dimy/imgy;
        count=countx*county;

        return count;

    }
}