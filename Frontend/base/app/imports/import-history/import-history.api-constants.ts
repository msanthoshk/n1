import { ImportHistoryBase } from "./import-history.base.model";

export class ImportHistoryApiConstants {
    public static readonly getDatatableData: any = {
        url: '/rest/rappitimports',
        method: 'POST',
        showloading: true
    };
}