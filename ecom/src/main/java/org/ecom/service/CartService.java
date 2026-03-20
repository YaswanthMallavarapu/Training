package org.ecom.service;

import org.ecom.dto.Item;
import org.ecom.exception.ItemNotFoundException;
import org.ecom.model.CartItem;
import org.ecom.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository=cartRepository;
    }

    @Transactional
    public void addCartItem(CartItem cartItem) {
        int userId=cartRepository.addUser(cartItem.getUser());
        cartRepository.addItem(cartItem,userId);
    }

    public List<Item> getAllCartItems() {
        return cartRepository.getAllItems();
    }

    public List<Item> getItemByUserName(String username) {
        return cartRepository.getItemsByName(username);
    }

    public void deleteItembyId(int id) throws ItemNotFoundException {

        boolean status= cartRepository.checkId(id);
        if(!status){
            throw new ItemNotFoundException("item with id not found");
        }else{
            cartRepository.deleteItem(id);

        }

    }
}
