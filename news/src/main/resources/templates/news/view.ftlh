<#import "../layout/main.ftlh" as main>
<@main.layout "Список карточек">
    <p>${news.publishDate!""}</p>
        <div class="row">
                <div class="col-md-4 mb-3">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-title" style="display: inline-block">
                                <img src="${news.imagePath}" style="max-width: 100%">
                                <h5>${news.title}</h5>
                                <p>${news.description}</p>
                                <p>${news.description2}</p>
                            </div>
                        </div>
                    </div>
                </div>
        </div>

    <a href="/news" class="btn btn-secondary">Назад</a>

</@main.layout>
