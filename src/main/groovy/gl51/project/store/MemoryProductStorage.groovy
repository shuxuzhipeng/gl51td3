package gl51.project.store
class MemoryProductStorage implements  ProductStorage {
	private List<Product> productlist = []
	@Override
    String save(Product p) {
      if(!p.id)
        p.id = UUID.randomUUID().toString()
        this.productlist.add(p)
        return p.id
    }
 
	
    @Override
    void update(String id, Product p) {
		for(e in productlist){
			if(e.id == id){
				e.name = p.name
				e.description = p.description
				e.price = p.price
				e.idealTemperature = p.idealTemperature
			}
		}
    }

    @Override
    Product getByID(String id) {
		def product = productlist.find { it.id == id }
        if(product == null)
        {
          throw new NotExistingProductException("The wanted product has not been found!")
        }
        return product
    }

    @Override
    void delete(String id) {
		def product = getByID(id)
		if (product != null){
			productlist.remove(product)
		}
    }

    @Override
    List<Product> all() {
		return productlist
    }
    
}
