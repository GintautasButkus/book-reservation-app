import axios from "../api/axios";
import authHeader from "./auth-header";



const REST_API_URL = 'http://localhost:8080';

class BookService {
    getBook(id){
        return axios.get(REST_API_URL + '/api/admin/book' + id )
}

    getAllBooks(){
        return axios.get(REST_API_URL + '/api/admin/books')
    }

    getRestaurants(){
        return axios.get(REST_API_URL + "/api/admin/restaurants")
    }

    deleteBook(id){
        return axios.delete(REST_API_URL + "/api/admin/delete/" + id)
    }

    addBookt(book, categoryId){
        return axios.post(REST_API_URL + "/api/admin/book" + categoryId, book)
    }

    updateBook(id, book, categoryId){
        return axios.put(REST_API_URL + "/api/admin/book/" + id, book, categoryId)
    }
}

export default new BookService();




