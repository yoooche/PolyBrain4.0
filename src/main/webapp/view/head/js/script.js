const cart = {
    items: {}
};

function addToCart(productID, quantity) {
    if (cart.items[productID]) {
        cart.items[productID] += quantity;
    } else {
        cart.items[productID] = quantity;
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    updateCartUI();
}

function fetchProductData(productID) {
    const url = `https://your-api-endpoint.com/products/${productID}`;

    return fetch(url)
        .then(response => response.json())
        .then(data => {
            return data;
        })
        .catch(error => {
            console.error('Error fetching product data:', error);
        });
}

function updateCartUI() {
    const cartItemsDiv = document.getElementById('cart-items');
    cartItemsDiv.innerHTML = '';

    for (const productID in cart.items) {
        fetchProductData(productID)
            .then(productData => {
                const cartItemDiv = document.createElement('div');
                cartItemDiv.classList.add('cart-item'); // 新增 CSS 類別
                cartItemDiv.innerHTML = `
            <p>${productData.name} x ${cart.items[productID]}</p>
            <button class="remove-item" data-product="${productID}">移除</button>
          `;
                cartItemsDiv.appendChild(cartItemDiv);

                const removeButton = cartItemDiv.querySelector('.remove-item');
                removeButton.addEventListener('click', () => {
                    removeFromCart(productID);
                });
            });
    }
}

function updateCartUI() {
    const cartItemsDiv = document.getElementById('cart-items');
    cartItemsDiv.innerHTML = '';

    for (const productID in cart.items) {
        fetchProductData(productID)
            .then(productData => {
                const cartItemDiv = document.createElement('div');
                cartItemDiv.innerHTML = `
            <p>${productData.name} x ${cart.items[productID]}</p>
          `;
                cartItemsDiv.appendChild(cartItemDiv);
            });
    }
}

// 初始化頁面，從 LocalStorage 中取得購物車內容並更新 UI
function initializePage() {
    const storedCart = localStorage.getItem('cart');
    if (storedCart) {
        cart = JSON.parse(storedCart);
        updateCartUI();
    }
}

// 頁面載入完成後初始化
document.addEventListener('DOMContentLoaded', () => {
    initializePage();
});
