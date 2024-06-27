// Sample data structure for items in categories
let categories = {
    1: [],
    2: [],
    3: []
};

// Function to handle category selection
function selectCategory() {
    const categoryId = document.getElementById('categorySelect').value;
    const operationsSection = document.getElementById('operationsSection');
    if (categoryId !== '') {
        operationsSection.style.display = 'block';
        showOperationsButtons();
    } else {
        operationsSection.style.display = 'none';
    }
}

// Function to show relevant operation buttons based on selected category
function showOperationsButtons() {
    const categoryId = document.getElementById('categorySelect').value;
    const displayBtn = document.getElementById('displayBtn');
    const updateBtn = document.getElementById('updateBtn');
    const deleteBtn = document.getElementById('deleteBtn');

    // Show relevant buttons based on the category
    if (categories[categoryId].length > 0) {
        displayBtn.style.display = 'inline-block';
        updateBtn.style.display = 'inline-block';
        deleteBtn.style.display = 'inline-block';
    } else {
        displayBtn.style.display = 'none';
        updateBtn.style.display = 'none';
        deleteBtn.style.display = 'none';
    }
}

// Function to add item to selected category
function addItem() {
    event.preventDefault();
    const categoryId = document.getElementById('categorySelect').value;
    const itemName = document.getElementById('itemName').value;
    if (itemName.trim() !== '') {
        categories[categoryId].push(itemName);
        showOperationsButtons();
        displayItems();
        document.getElementById('categoryForm').reset();
    }
}

// Function to display items in selected category
function displayItems() {
    const categoryId = document.getElementById('categorySelect').value;
    const itemList = document.getElementById('itemList');
    itemList.innerHTML = '';
    categories[categoryId].forEach(item => {
        const li = document.createElement('li');
        li.className = 'list-group-item';
        li.textContent = item;
        itemList.appendChild(li);
    });
}

// Function to update item in selected category (example)
function updateItem() {
    alert('Update functionality can be implemented based on your requirements.');
}

// Function to delete item from selected category (example)
function deleteItem() {
    alert('Delete functionality can be implemented based on your requirements.');
}
