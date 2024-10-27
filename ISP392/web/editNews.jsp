<div class="container mx-auto py-8">
    <h2 class="text-2xl font-bold text-gray-800 mb-6">Edit News</h2>

    <form action="news" method="post" class="bg-white p-6 rounded shadow-md">
        <input type="hidden" name="id" value="${news.id}">
        <input type="hidden" name="uploadDate" value="${news.uploadDate}"> <!-- Keep upload date -->

        <div class="mb-4">
            <label for="title" class="block text-gray-700 font-bold mb-2">Title</label>
            <input type="text" id="title" name="title" value="${news.title}" 
                   class="w-full p-2 border rounded" required>
        </div>

        <div class="mb-4">
            <label for="img" class="block text-gray-700 font-bold mb-2">Image Path</label>
            <input type="text" id="img" name="img" value="${news.img}" 
                   class="w-full p-2 border rounded" required>
        </div>

        <div class="mb-4">
            <label for="content" class="block text-gray-700 font-bold mb-2">Content</label>
            <textarea id="content" name="content" rows="5" 
                      class="w-full p-2 border rounded" required>${news.content}</textarea>
        </div>

        <button type="submit" name="action" value="edit" 
                class="bg-blue-500 text-white py-2 px-4 rounded">Update News</button>
    </form>
</div>
