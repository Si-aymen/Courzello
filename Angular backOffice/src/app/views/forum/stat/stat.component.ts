import { Component } from '@angular/core';
import { Question } from 'src/app/model/question';

import { QuestionServiceService } from 'src/app/service/question-service.service';


@Component({
  selector: 'app-stat',
  templateUrl: './stat.component.html',
  styleUrls: ['./stat.component.scss']
})
export class StatComponent {
  listofQuestion: Question[] = [];
  isReady: boolean = false;

  constructor(private ps: QuestionServiceService) {}

  ngOnInit(): void {
    this.getallQuestion();
  }

  updateChartDoughnutData() {
    this.chartDoughnutData.labels = [];
    this.chartDoughnutData.datasets[0].data = [];

    this.listofQuestion.forEach(question => {
      this.chartDoughnutData.labels.push(question.id);
      this.chartDoughnutData.datasets[0].data.push(question.votes?.length ?? 0);
    });
  }

  updateChartPieData() {
    this.chartPieData.labels = [];
    this.chartPieData.datasets[0].data = [];

    this.listofQuestion.forEach(question => {
      this.chartPieData.labels.push(question.id);
      this.chartPieData.datasets[0].data.push(question.reponses?.length ??0);
    });
  }

  getallQuestion() {
    this.ps.getQuestions().subscribe(data => {
      console.log(data);
      this.listofQuestion = data;
      this.updateChartDoughnutData();
      this.updateChartPieData();
      this.isReady = true;
    });
  }

  chartDoughnutData = {
    labels: [] as String[],
    datasets: [
      {
        backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
        data: [] as number[]
      }
    ]
  };

  chartPieData = {
    labels: [] as String[],
    datasets: [
      {
        data: [] as number[],
        backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
        hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
      }
    ]
  };
}
