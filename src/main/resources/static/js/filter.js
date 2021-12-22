function addList (arr, id)
{
    console.log("ddf"+arr.length);
    for( i = 0; i < arr.length; ++i) {
        let newOption = new Option(arr[i], arr[i]);
        id.append(newOption);
    }
}