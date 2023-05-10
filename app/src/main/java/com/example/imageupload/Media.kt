package com.example.imageupload

class Media:java.io.Serializable{
var id:Long=0
var displayName:String=""
var imagePath:String=""
var dateAdded:String=""
var mimeType:String=""
var isSelected:Boolean = false
constructor(){
    // empty constructor
}
constructor(
    id: Long,
    displayName: String,
    imagePath: String,
    dateAdded: String,
    mimeType: String
){
    this.id = id
    this.displayName = displayName
    this.imagePath = imagePath
    this.dateAdded = dateAdded
    this.mimeType = mimeType
}}