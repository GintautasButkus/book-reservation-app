import React, {useState, useEffect} from 'react'
import {Link, useNavigate, useParams } from 'react-router-dom';
import axios from '../api/axios';
import bookService from '../services/book.service';




const AddBook = () => {

    const [name, setName] = useState('')
    const [isbn, setIsbn] = useState('')
    const [pages, setPages] = useState('')
    const [foto, setFoto] = useState('')
    const [status, setStatus] = useState('')
    const [category, setCategory] = useState('')
    const history = useNavigate();
    const {id} = useParams();

    const saveOrUpdateRestaurant = (e) => {
        e.preventDefault();

        const book = {name, isbn, pages, foto, status, category}

        if(id){
            bookService.updateBook(id, book, category).then((response) =>{
                console.log(response.data)
                history.push('/admin')
            }).catch(error => {
                console.log(error)
            })

        }else{
            bookService.addBookt(book).then((response) =>{

                console.log(response.data)
    
                history.push('/admin');
    
            }).catch(error => {
                console.log(error)
            })
        }
    }

    useEffect(() => {

        bookService.getBook(id).then((response) =>{
            setName(response.data.name)
            setIsbn(response.data.isbn)
            setPages(response.data.pages)
            setFoto(response.data.foto)
            setStatus(response.data.status)
            setCategory(response.data.category)
           
            
        }).catch(error => {
            console.log(error)
        })
    }, [])

    const title = () => {

        if(id){
            return <h2 className = "text-center">Koreguoti restoraną</h2>
        }else{
            return <h2 className = "text-center">Pridėti restoraną</h2>
        }
    }

    return (
        <div>
           <br /><br />
           <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3 offset-md-3">
                       {
                           title()
                       }
                        <div className = "card-body">
                            <form>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Pavadinimas :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Knygos pavadinimas"
                                        name = "pavadinimas"
                                        className = "form-control"
                                        value = {name}
                                        onChange = {(e) => setName(e.target.value)}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label">ISBN :</label>
                                    <input
                                        type = "text"
                                        placeholder = "ISBN"
                                        name = "isbn"
                                        className = "form-control"
                                        value = {isbn}
                                        onChange = {(e) => setIsbn(e.target.value)}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Puslapiai :</label>
                                    <input
                                        type = "number"
                                        placeholder = "Puslapių skaičius"
                                        name = "ppuslapiai"
                                        className = "form-control"
                                        value = {pages}
                                        onChange = {(e) => setPages(e.target.value)}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Statusas :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Statusas"
                                        name = "status"
                                        className = "form-control"
                                        value = {status}
                                        onChange = {(e) => setStatus(e.target.value)}
                                    >
                                    </input>
                                </div>


                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Kategorija :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Kategorija"
                                        name = "kategorija"
                                        className = "form-control"
                                        value = {category}
                                        onChange = {(e) => setCategory(e.target.value)}
                                    >
                                    </input>
                                </div>

                               

                                <button className = "btn btn-success" onClick = {(e) => saveOrUpdateRestaurant(e)} >Patvirtinti </button>
                                <Link to="/admin" className="btn btn-danger"> Atšaukti </Link>
                            </form>

                        </div>
                    </div>
                </div>

           </div>

        </div>
    )
}

export default AddBook