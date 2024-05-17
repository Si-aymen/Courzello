
import pickle
from flask import Flask, request, jsonify, render_template
from flask_cors import CORS


app = Flask(__name__)
CORS(app)

# Load the data
courses = pickle.load(open("courses_list.pkl", "rb"))
similarity = pickle.load(open("similarity.pkl", "rb"))

courses_list = courses['course_title'].values

# Function to recommend courses
def recommend(course):
    index_list = courses[courses['course_title'] == course].index.tolist()
    if not index_list:
        raise IndexError("Course not found")
    index = index_list[0]

    distance = sorted(list(enumerate(similarity[index])), reverse=True, key=lambda vector: vector[1])
    recommended_courses = [courses.iloc[i[0]].course_title for i in distance[:5]]
    return recommended_courses

@app.route('/', methods=['GET', 'POST'])
def index():
    recommended_courses = []
    selected_course = None

    if request.method == 'POST':
        selected_course = request.form['course']
        recommended_courses = recommend(selected_course)

    return render_template('index.html', courses_list=courses_list, selected_course=selected_course, recommended_courses=recommended_courses)

@app.route('/recommend', methods=['POST'])
def recommend_course_post():
    data = request.get_json()
    course_name = data.get('course_name')

    if not course_name:
        return jsonify({"error": "Course name is required"}), 400

    try:
        recommended_courses = recommend(course_name)
        return jsonify({
            "course_name": course_name,
            "recommended_courses": recommended_courses
        })
    except IndexError:
        return jsonify({"error": "Course not found"}), 404

@app.route('/recommend/<string:course_name>', methods=['GET'])
def recommend_course_get(course_name):
    try:
        recommended_courses = recommend(course_name)
        return jsonify({
            "course_name": course_name,
            "recommended_courses": recommended_courses
        })
    except IndexError:
        return jsonify({"error": "Course not found"}), 404
    



if __name__ == '__main__':
    app.run(debug=True)
