import React, {useState, useEffect} from 'react'
import { Link } from 'react-router-dom';
import axios from "../api/axios";

import {MdDelete} from "react-icons/md";
import bookService from '../services/book.service';




function Admin() {

    const [books, setBook] = useState([])
 

    useEffect(() => {
        getBooks()
    }, [])

    const getBooks = () => {
        bookService.getAllBooks().then((response) => {
            setBook(response.data)
            console.log(response.data);
        });
    };

    const handleDelete = (categoryId, book) => {
        bookService.deleteBook(categoryId, book).then((response) => {
            getBooks();
        }).catch(error => {
            console.log(error)
        })
    }

    return (
        <div className = "container">
            
            <h1 className = "text-center"> Administruojamos Knygos</h1>
            <Link to = "/add-book" className = "btn btn-primary mb-2" > Nauja Knyga </Link>

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
                                        <Link to={`/menu/${book.id}`} className='link-primary'>Meniu</Link>
                                        <MdDelete style={{color: "orange"}} size={'1.4em'} onClick={() =>handleDelete(book.id)}/>
                                        <Link className="btn btn-info" to={`/edit-book/${book.id}`} >Keisti</Link>
                                    </td>
                                    

                                </tr>

                        )
                    }

                </tbody>


            </table>

        </div>
    )
}

export default Admin