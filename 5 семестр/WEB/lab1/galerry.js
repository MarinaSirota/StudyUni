let corpusheads = document.getElementsByClassName('corpushead');

for(let i = 0; i < corpusheads.length; i++){
    corpusheads[i].addEventListener('click', e => {
        let corpuss = document.getElementsByClassName('corpus');
        let id = corpusheads[i].id.split('-')[1];
        for(let corpus of corpuss){
            corpus.style.display = 'none';
        }
        corpus = document.getElementById(`corpus-${id}`);

        corpus.style.display = 'block';
    }, false);
}