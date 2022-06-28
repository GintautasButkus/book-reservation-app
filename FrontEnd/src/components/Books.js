import React, {useState, useEffect} from 'react'
import { Link } from 'react-router-dom';
import axios from "../api/axios";


function Books() {

    const [books, setBooks] = useState([])
    const ALL_BOOKS = "/api/auth/user/restaurants"

    useEffect(() => {
        getBooks()
    }, [])

    const getBooks = () => {

        axios.get(ALL_BOOKS).then((response) => {
            setBooks(response.data)
            console.log(response.data);
        });
    };

    return (
        <div className = "container">
            
            <h1 className = "text-center"> Restoranai</h1>

            <table className = "table table-striped">
                <thead>
                    <tr>
                        <th> Knygos nr.</th>
                        <th> Pavadinimas</th>
                        <th> ISBN</th>
                        <th> Puslapių skaičius</th>
                        <th> Foto</th>
                        <th> Statusas </th>
                        <th> Kategorija</th>
                    </tr>

                </thead>
                <tbody>
                    {
                       books.map(
                                book =>
                                <tr key = {book.id}>
                                    <td> {book.id }</td>
                                    <td> {book.booksName}</td>
                                    <td> {book.isbn}</td>   
                                    <td> {book.pageAmount}</td>  
                                    <td> {book.photo}</td>
                                    <td> {book.status}</td>
                                    <td> {book.booksCategory}</td>     
                                      
                                    <td>
                                        <Link to={`/menu/${book.id}`} className='link-primary'>Rezervuoti</Link>
                                    </td>
                                    

                                </tr>

                        )
                    }

                </tbody>


            </table>

        </div>
    )
}

export default Books