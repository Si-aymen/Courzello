import { Component, OnInit, AfterContentInit, ChangeDetectorRef, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { cibFacebook, cibLinkedin, cibTwitter } from '@coreui/icons';

@Component({
  selector: 'app-classroom',
  templateUrl: './classroom.component.html',
  styleUrls: ['./classroom.component.scss']
})
export class ClassroomComponent implements OnInit, AfterContentInit {

  classroomArray: any[] = [];
  classroomCapacity: number = 0;
  classroomName: String = "";
  classroomLvl: String = "";
  speciality: string = "";
  classroomId: string = "";
  CurrentClassroomID = "";
  chartPieData: any;
  chartDoughnutData: any;



  constructor(private http: HttpClient, private changeDetectorRef: ChangeDetectorRef) {
    this.GetAllClassoom();

  }



  ngOnInit(): void {}

  register() {
    let bodyData = {
      "classroomCapacity": this.classroomCapacity,
      "classroomName": this.classroomName,
      "classroomLvl": this.classroomLvl
    };

    this.http.post("http://localhost:8090/pi/classrooms/Save", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("Classroom Registered Successfully");
      this.GetAllClassoom();
      this.classroomCapacity = 0;
      this.classroomName = '';
      this.classroomLvl = '';
    });
  }

  GetAllClassoom() {
    this.http.get("http://localhost:8090/pi/classrooms").subscribe((resultData: any) => {
      console.log(resultData);
      this.classroomArray = resultData;
      this.generateChartPieData();
      this.generateChartDoughnutData();
    });
  }

  setDelete(data: any) {
    this.http.delete("http://localhost:8090/pi/classrooms/DeleteClassroom/" + data._, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("Classroom Deleted");
      this.GetAllClassoom();
      this.classroomName = '';
      this.classroomLvl = '';
      this.classroomCapacity = 0;
      this.classroomId = '';
    });
  }
 


  generateChartDoughnutData() {
    const nameCounts: { [name: string]: number } = {};
    this.classroomArray.forEach(classroom => {
      const name = classroom.classroomLvl;
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

  generateChartPieData() {
    const nameCounts: { [name: string]: number } = {};
    this.classroomArray.forEach(classroom => {
      const name = classroom.classroomName;
      nameCounts[name] = (nameCounts[name] || 0) + 1;
    });

    this.chartPieData = {
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



/*


  icons = { cibFacebook, cibLinkedin, cibTwitter };

  @Input() withCharts?: boolean = true;

  // @ts-ignore
  chartOptions = {
    elements: {
      line: {
        tension: 0.4
      },
      point: {
        radius: 0,
        hitRadius: 10,
        hoverRadius: 4,
        hoverBorderWidth: 3
      }
    },
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: false
      }
    },
    scales: {
      x: {
        display: false
      },
      y: {
        display: false
      }
    }
  };
  labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
  datasets = {
    borderWidth: 2,
    fill: true
  };
  colors = {
    backgroundColor: 'rgba(255,255,255,.1)',
    borderColor: 'rgba(255,255,255,.55)',
    pointHoverBackgroundColor: '#fff'
  };
  brandData = [
    {
      icon: this.icons.cibFacebook,
      values: [{ title: 'friends', value: '89K' }, { title: 'feeds', value: '459' }],
      capBg: { '--cui-card-cap-bg': '#3b5998' },
      labels: [...this.labels],

      data: {
        labels: [...this.labels],
        datasets: [{ ...this.datasets, data: [65, 59, 84, 84, 51, 55, 40], label: 'Facebook', ...this.colors }]
      }
    },
    {
      icon: this.icons.cibTwitter,
      values: [{ title: 'followers', value: '973k' }, { title: 'tweets', value: '1.792' }],
      capBg: { '--cui-card-cap-bg': '#00aced' },

      data: {
        labels: [...this.labels],
        datasets: [{ ...this.datasets, data: [1, 13, 9, 17, 34, 41, 38], label: 'Twitter', ...this.colors }]
      }
    },

  ];

  capStyle(value: string) {
    return !!value ? { '--cui-card-cap-bg': value } : {};
  }
*/
  ngAfterContentInit(): void {
    this.changeDetectorRef.detectChanges();
  }

}




