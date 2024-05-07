import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router,ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-chapter',
  templateUrl: './chapter.component.html',
  styleUrl: './chapter.component.scss'
})
export class ChapterComponent implements OnInit {
  

  chapterArray: any[] = [];
  chapterName: String = "";
  duration : number = 0 ; 
  CurrentchapterID = "";
  chartDoughnutData :any ;
  chartDoughnutData2 :any ;
  courseID : String ; 


  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.GetAllchapter();
  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.courseID = params['id']; 
      this.GetAllchapter();
    });

  }




  GetAllchapter() {
    this.http.get("http://localhost:8090/pi/chapters/retrieve-chapters").subscribe((resultData: any) => {
      console.log(resultData);
      this.chapterArray = resultData;
      this.generateChartDoughnutData();
      this.generateChartDoughnutData2();

    
    });
  }
  add() {
    let bodyData = {
      "chapterName": this.chapterName,
      "duration": this.duration
    };

    this.http.post("http://localhost:8090/pi/chapters/add-chapter", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("chapter added Successfully");
      this.GetAllchapter();
      this.chapterName = '';
      this.duration = 0;
      

    });
  }


  generateChartDoughnutData() {
    const nameCounts: { [name: string]: number } = {};
    this.chapterArray.forEach(chapter => {
      const name = chapter.chapterName;
      nameCounts[name] = (nameCounts[name] || 0) + 1;
    });

    this.chartDoughnutData = {
      labels: Object.keys(nameCounts),
      datasets: [{
        data: Object.values(nameCounts),
        backgroundColor: this.generateRandomColors(Object.keys(nameCounts).length),
        hoverBackgroundColor: this.generateRandomColors(Object.keys(nameCounts).length)
      }]
    };
  }


  generateChartDoughnutData2() {
    const nameCounts: { [name: string]: number } = {};
    this.chapterArray.forEach(chapter => {
      const name = chapter.duration;
      nameCounts[name] = (nameCounts[name] || 0) + 1;
    });

    this.chartDoughnutData2 = {
      labels: Object.keys(nameCounts),
      datasets: [{
        data: Object.values(nameCounts),
        backgroundColor: this.generateRandomColors(Object.keys(nameCounts).length),
        hoverBackgroundColor: this.generateRandomColors(Object.keys(nameCounts).length)
      }]
    };
  }

  generateRandomColors(numColors: number): string[] {
    const colors: string[] = [];
    for (let i = 0; i < numColors; i++) {
      colors.push('#' + Math.floor(Math.random() * 16777215).toString(16));
    }
    return colors;
  }


  Affectchapter(chapterID : String ){
    this.http.put("http://localhost:8090/pi/courses/AddChapter/" +this.courseID+ "/" +chapterID, {}).subscribe((resultData: any) => {
      console.log(resultData);
      console.log("course ID "+this.courseID);
      console.log("ChapterID " + chapterID);
      alert("chapter added Successfully");

    });
  }


}
