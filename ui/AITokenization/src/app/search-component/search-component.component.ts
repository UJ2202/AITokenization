import { Component, OnInit } from '@angular/core';
import { ControllerServiceService } from '../service/controller-service.service';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';


@Component({
  selector: 'app-search-component',
  standalone: true,
  imports: [RouterOutlet,FormsModule,CommonModule],

  templateUrl: './search-component.component.html',
  styleUrl: './search-component.component.scss'
})
export class SearchComponentComponent implements OnInit {
  apiResponse :any;
  search:String='Search'
  searchText: string = '';
  tokenizers: string[] = []; // Example tokenizers
  selectedTokenizer: string = '';
  tokenizerKeys:[]=[];
  selectedEncoding: string = '';
  responseMessage: string = '';
  isSuccess: boolean = false;

  constructor(private apiService: ControllerServiceService) {}

  ngOnInit(): void {
    this.apiService.getTokenizers().subscribe(res=>{
      this.apiResponse=res;
      this.tokenizers= Object.keys(res);
      this.selectedTokenizer=this.tokenizers[0];
      this.onTokenizerGroupSelect()
    })
   
  }

  onTokenizerGroupSelect(): void {
    this.tokenizerKeys = this.apiResponse[this.selectedTokenizer] || [];
    this.selectedEncoding = '';
  }
 
  onSearch(): void {
    this.search='Searching';
    this.responseMessage='';
    const payload = {
      userPrompt: this.searchText,
      tokenizerName: this.selectedTokenizer,
      encodingType: this.selectedEncoding
    };

    this.apiService.search(payload).subscribe(
      (response) => {
        this.responseMessage = 'Success: Number of tokens are ' + response;
        this.isSuccess = true;
        this.search='Search';
      },
      (error) => {
        this.responseMessage = 'Error: Number of Tokens are ' + error.error.message || 'An error occurred';
        this.isSuccess = false;
        this.search='Search';
      }
    );
  }
}